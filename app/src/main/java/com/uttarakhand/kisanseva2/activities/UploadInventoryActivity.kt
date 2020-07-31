package com.uttarakhand.kisanseva2.activities

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
import android.widget.ProgressBar
import android.widget.Toast
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
        } else if (etQualityIn.text!!.toString() == "") {
            etQuality.error = "Add Quality"
            etQuality.requestFocus()
        } else if (etQuantityIn.text!!.toString() == "") {
            etQuantity.error = "Add Quantity Available"
            etQuantity.requestFocus()
        } else if (etPriceIn.text!!.toString() == "") {
            etPrice.error = "Add price of item per Kg"
            etPrice.requestFocus()
        } else if (etDescriptionIn.text!!.toString().equals("")) {
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
                        etQualityIn.text!!.toString(),
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
                            startActivity(Intent(this@UploadInventoryActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                })
    }
    private fun removeLoading(){
        btnUploadItem.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }
    private fun addLoading(){
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
                etQualityIn.setText(qualities[getRandomNumber(0, 3)])
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