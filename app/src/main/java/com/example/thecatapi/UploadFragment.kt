package com.example.thecatapi

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.thecatapi.database.ApiClient
import com.example.thecatapi.model.ResponseItem
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


class UploadFragment : Fragment() {
    lateinit var root: View
    lateinit var image_upload: ImageView
    lateinit var button: Button
    var check = false
    lateinit var image: File
    lateinit var imageUri1: Uri
    lateinit var add_linear: LinearLayout
    val PICK_IMAGE = 121
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_upload, container, false)
        initViews()
        return root
    }

    private fun initViews() {
        image_upload = root.findViewById<ImageView>(R.id.image_upload)
         button = root.findViewById<Button>(R.id.upload_btn)
        button.setOnClickListener{
            if (check){

                val file = image

                val reqFile: RequestBody = RequestBody.create(MediaType.parse("image/jpg"), file)
                val body: MultipartBody.Part =
                    MultipartBody.Part.createFormData("file", file.name, reqFile)

                val apiServise = ApiClient(requireContext()).apiServise
                apiServise.uploadFile(body, "diyorbek").enqueue(object : Callback<ResponseItem> {
                    override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                        if (response.isSuccessful) {
                            Log.d("TAG", "onResponse: ${response.body()}")
                            Toast.makeText(requireContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        } else {
                            check =false
                            add_linear.visibility = View.VISIBLE
                            button.visibility = View.GONE
                            image_upload.setImageDrawable(null)
                        }
                    }

                    override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                        Log.d("TAG", "onFailure: $t")
                        button.visibility = View.INVISIBLE
                        check =false
                        add_linear.visibility = View.VISIBLE
                        image_upload.setImageDrawable(null)
                    }

                })
            }
        }
        add_linear = root.findViewById(R.id.add_liner)
        image_upload.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    listOf(Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray(),
                    2000
                )
            } else {
                getImageFromGallery()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            try {
                val imageUri: Uri = data!!.data!!
                imageUri1 = imageUri
                button.visibility = View.VISIBLE
                check = true
                add_linear.visibility = View.GONE
                image_upload.setImageURI(imageUri)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                check = false
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun getImageFromGallery() {
        getImageFromGallery.launch("image/*")
    }

    private val getImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri ?: return@registerForActivityResult
        val ins = requireActivity().contentResolver.openInputStream(uri)
        image = File.createTempFile("file", ".jpg", requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        val fileOutputStream = FileOutputStream(image)
        ins?.copyTo(fileOutputStream)
        ins?.close()
        fileOutputStream.close()
        if (image.length() == 0L) return@registerForActivityResult
        Glide.with(requireActivity()).load(image).into(image_upload)
        button.visibility = View.VISIBLE
        check =true
        add_linear.visibility = View.GONE
    }
}
