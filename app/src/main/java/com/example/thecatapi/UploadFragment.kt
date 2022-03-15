package com.example.thecatapi

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.FileNotFoundException


class UploadFragment : Fragment() {
    lateinit var root: View
    lateinit var image_upload: ImageView
    lateinit var button: Button
    var check = false
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

            }
        }
        add_linear = root.findViewById(R.id.add_liner)
        image_upload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            try {
                val imageUri: Uri = data!!.data!!
                imageUri1 = imageUri
                button.visibility = View.VISIBLE
                check =true
                add_linear.visibility = View.GONE
                image_upload.setImageURI(imageUri)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                check = false
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            check = false
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
