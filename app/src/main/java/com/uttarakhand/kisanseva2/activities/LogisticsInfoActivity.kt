package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.LogisticAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.LogisticInfo
import kotlinx.android.synthetic.main.activity_logistics_info.*

class LogisticsInfoActivity : AppCompatActivity() {


    private var logList: ArrayList<LogisticInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logistics_info)

        initData()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val logAdapter = LogisticAdapter(logList!!, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = logAdapter
    }


    private fun initData() {
        logList = ArrayList()
        logList!!.add(LogisticInfo("Rivigo Services Pvt Ltd", "relay.rivigo.com", "Rivigo is a technology company that is building the material movement pipeline of India through a globally unique innovation of relay trucking, enabled by a strong interplay of technology, data, culture and operational excellence with 3000+ entirely own fleet of vehicles. Their goal is making logistics human, faster, safer and cost-effective."))
        logList!!.add(LogisticInfo("Truck Suvidha", " www.trucksuvidha.com", "TruckSuvidha is a one stop solution for anyone who is looking for Trucks. We have over 38,000+ transporters in our network which constitute a network of more than 3,50,000 trucks all across the country. TruckSuvidha's aim is to organize an un-organized sector providing easy booking of vehicles and boost the business of people. Using TruckSuvidha Farmers, APMC, Traders and other related entities can directly get in touch with fleet owners all across the country. People can book trucks via mobile application, website or calling 8882080808."))
        logList!!.add(LogisticInfo("Transin Logistics", "www.transin.in", "In Brief , Transin Logistics is technology enabled long haul logistics company servicing 45+ blue chip companies across the length and breadth of India. We are a 300 Crore company with 3000 transporters 13,000 trucks on our network. We have deep expertise in handling cargo like Steel,Cement, Paints,FMCG, Batteries, Dairy and Transformers for various customers. We operate out of 35 loading locations in India. We move 3.5+mil tons of RM/FG commodities across metals and mineral, FMCG/FMCD and Agro sectors every year."))
        logList!!.add(LogisticInfo("Truck Guru", "www.truckguru.co.in", "Having 100+ Transporters and 700+ Trucks linked, TruckGuru is amongst the most renowned and appreciated cargo service providers in the industry. We started our journey with the aim of delivering the logistics and transportation excellence to its customers ensuring their comfort as well as success."))
        logList!!.add(LogisticInfo("Elastic Run", "www.elastic.run", "ElasticRun is an aggregated variable capacity transportation network built using idle transportation and logistics capacities from large number of dispersed entrepreneurs with 700+ Transporters and 3000+ Trucks. ElasticRun caters to industries across the board, including FMCG, Food, Manufacturing and E-commerce."))
        logList!!.add(LogisticInfo("BlackBuck", "www.blackbuck.com", "Founded in 2015, Blackbuck today is India’s largest trucking platform with more than 1,50,000 transporters, 4,00,000 registered trucks and 10,000 customers which include SMEs, traders and large corporates like Amul, Asian Paints, HUL, ITC, Reliance and others. We receive orders from customers via email, our mobile app or on call and we match a truck against it. We service across all states in India, with our presence in more than 400 districts. To book a truck, call on +91 80 46481838. "))
        logList!!.add(LogisticInfo("Mavyn", "www.mavyn.in", "Mavyn is a technology-driven logistics company that focuses on truckload (more than 800 km) transportation in India. Mavyn aims to be the largest truckload platform in India, having its network spread across all districts of India with 1000 Transporters & 7000+ Trucks linked. Mavyn is the first internet company in India to apply Artificial Intelligence (AI) and big data in the truckload logistics sector.You can also book a truck on our 24*7 helpline Number - +91 9711326777 "))
        logList!!.add(LogisticInfo("Jusda India", "www.jusdaindia.com", "Jusda Supply Chain (A Foxconn group company) is one of the world’s leading logistics companies. We are a tech focused company providing end to end logistics solutions including Trucking, Warehousing, B2B delivery, Enterprise logistics, Air freight and Ocean freight. We have a network of 38,000+ verified transporters with 3,50,000+ registered trucks available all across the country. To book a truck, pl write to us at contact@jusdaindia.com or call on +91 124 4751800"))
        logList!!.add(LogisticInfo("eParivahan", "www.eparivahan.com", "eParivahan is an end to end Logistics automation platform providing vehicle procurement, placement and tracking services to corporates and SMEs.We have a presence in West Bengal, Bihar, Uttar Pradesh, Madhya Pradesh, Gujrat, Maharastra, Karnataka, Tamil Nadu, etc with a network of 500+ transporters & 11000+ fleet under our Management. Trader/FPO/Companies can contact eParivahan for their Procurement & Tracking needs. For Booking call us at +91 8584077771"))

    }
}