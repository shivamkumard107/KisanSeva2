package com.uttarakhand.kisanseva2.activities.inventoryManagement

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.model.uploadItem.ItemUploadInventory
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_upload_inventory.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class UploadInventoryActivity : AppCompatActivity() {
    private val GALLERY_CODE = 1
    private var imageSelected: Boolean = false
    private val qualities = arrayOf<String?>("Elite", "Premium", "Classic")
    private val type = arrayOf<String?>("Select type", "vegetable", "grain", "fruit")

    private var mProgressBar: ProgressBar? = null
    private var mImageUri: Uri? = null
    private var mStorageRef: StorageReference? = null
    private var mUploadTask: StorageTask<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_inventory)
        val colToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        colToolbar.title = getString(R.string.add_in_inventory)
        colToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorTitle))
        colToolbar.setExpandedTitleColor(Color.TRANSPARENT)
        toolbar.setNavigationOnClickListener { v1: View? -> finish() }
        val appBarLayout: AppBarLayout = findViewById(R.id.app_bar_layout)

        //spinner
        val spin = findViewById<View>(R.id.spinner_type) as Spinner
//        spin.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the country list
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, type)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spin.adapter = aa

        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout1: AppBarLayout, verticalOffset: Int ->
            if (Math.abs(verticalOffset) == appBarLayout1.totalScrollRange) {
                // If collapsed, then do this
                toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
            } else if (verticalOffset == 0) {
                // If expanded, then do this
                toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_cross)
            } else {
                // Somewhere in between
                // Do according to your requirement
            }
        })
        mProgressBar = findViewById(R.id.progress_bar)
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads")

        item_image.setOnClickListener { requestPermission() }
        btnUploadItem.setOnClickListener { verifyDetails() }
    }

    private fun verifyDetails() {
        if (!imageSelected) {
            Toast.makeText(this, "Upload product image", Toast.LENGTH_SHORT).show()
        } else if (etNameIn.text.toString() == "") {
            etName.error = "Add Item Name"
            etName.requestFocus()
        } else if (etCategoryIn.text!!.toString() == "") {
            etCategory.error = "Add Category"
            etCategory.requestFocus()
        } else if (etDescIn.text!!.toString() == "") {
            etDesc.error = "Add Quality"
            etDesc.requestFocus()
        } else if (etQuantityIn.text!!.toString() == "") {
            etQuantity.error = "Add Quantity Available"
            etQuantity.requestFocus()
        } else if (etPriceIn.text!!.toString() == "") {
            etPrice.error = "Add price of item per Kg"
            etPrice.requestFocus()
        } else if (spinner_type.selectedItem.toString() == getString(R.string.select_type)) {
            Toast.makeText(this, "Select a Type", Toast.LENGTH_SHORT).show()
        } else if (etDescriptionIn.text!!.toString() == "") {
            etDescription.error = "Add a small description of item"
            etDescription.requestFocus()
        } else {
            //Upload
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT).show()
            } else {
                addLoading()
                uploadFile()
            }
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun uploadFile() {
        if (mImageUri != null) {
            val fileReference = mStorageRef!!.child(System.currentTimeMillis()
                    .toString() + "." + getFileExtension(mImageUri!!))
            mUploadTask = fileReference.putFile(mImageUri!!)
                    .addOnSuccessListener { taskSnapshot ->
                        val handler = Handler()
                        handler.postDelayed({ mProgressBar!!.progress = 0 }, 500)
                        Toast.makeText(this, "Upload successful", Toast.LENGTH_LONG).show()
                        if (taskSnapshot.metadata != null) {
                            if (taskSnapshot.metadata!!.reference != null) {
                                val result = taskSnapshot.storage.downloadUrl
                                result.addOnSuccessListener { uri ->
                                    uploadToServer(uri!!)
                                }
                            }
                        }
                    }
                    .addOnFailureListener { e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        mProgressBar!!.progress = progress.toInt()
                    }
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadToServer(uri: Uri) {

        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.uploadingItemInventory(
                        uri.toString(),
                        etNameIn.text!!.toString(),
                        etDescriptionIn.text!!.toString(),
                        spinner_type.selectedItem.toString(),
                        etDescIn.text!!.toString(),
                        etQuantityIn.text!!.toString(),
                        etPriceIn.text!!.toString(),
                        etCategoryIn.text!!.toString())
                ?.enqueue(object : Callback<ItemUploadInventory> {
                    override fun onFailure(call: Call<ItemUploadInventory>, t: Throwable) {
                        Toast.makeText(this@UploadInventoryActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("ItemUploadFail", t.message!!)
                        removeLoading()
                    }

                    override fun onResponse(call: Call<ItemUploadInventory>, response: Response<ItemUploadInventory>) {
                        removeLoading()
                        Toast.makeText(this@UploadInventoryActivity, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d("ItemUploadSuc", response.body()!!.toString())
                        if (response.message().equals("OK")) {
                            Toast.makeText(this@UploadInventoryActivity, "Crop Uploaded Successfully", Toast.LENGTH_LONG).show()
                            giveClusterDialog()
                        }
                    }
                })
    }

    private fun giveClusterDialog() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Cluster Joining Request")
        //set message for alert dialog
        builder.setMessage("Your uploaded quantity is less than ${etQuantityIn.text!!.toString()}, so we recommend you to join the nearby ALMORA cluster 15")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //performing positive action
        builder.setPositiveButton("Yes, Send Request") { dialogInterface, which ->
            Toast.makeText(applicationContext, "Request Sent Successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@UploadInventoryActivity, MainActivity::class.java))
            finish()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            startActivity(Intent(this@UploadInventoryActivity, MainActivity::class.java))
            finish()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun removeLoading() {
        btnUploadItem.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    private fun addLoading() {
        btnUploadItem.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    private val PICK_IMAGE_REQUEST = 1

    private fun openFileChooser() {
        imageSelected = false
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mImageUri = data.data
            Picasso.get().load(mImageUri).into(item_image)
            ml.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed({
                ml.visibility = View.GONE
                etDescIn.setText(qualities[getRandomNumber(0, 3)])
                etPriceIn.setText(getString(R.string.predicted_price))
                Toast.makeText(this, getString(R.string.price_filling_toast), Toast.LENGTH_LONG).show()
                imageSelected = true
            }, 4000)
        }
    }


    private val REQUEST_WRITE_PERMISSION = 786

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openFileChooser()
        }
    }


    fun getRandomNumber(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }
}