package com.o7services.recyclercrud

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.recyclercrud.databinding.ActivityMainBinding
import com.o7services.recyclercrud.databinding.AddLayoutBinding
import com.o7services.recyclercrud.databinding.EditLayoutBinding

class MainActivity : AppCompatActivity(),listClick {
    lateinit var binding: ActivityMainBinding
    lateinit var studentAdapter: StudentAdapter
    var StudentList = ArrayList<StudentModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentAdapter = StudentAdapter(StudentList, this)
        binding.recycle.layoutManager = LinearLayoutManager(this)
        binding.recycle.adapter = studentAdapter

        binding.fabBtn.setOnClickListener {
            val dialogBinding = AddLayoutBinding.inflate(layoutInflater)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etName.text.isEmpty()) {
                    dialogBinding.etName.error = "Enter your name"
                } else if (dialogBinding.etRollNo.text.isEmpty()) {
                    dialogBinding.etRollNo.error = "enter your roll no"
                } else {
                    StudentList.add(
                        StudentModel(
                            dialogBinding.etName.text.toString(),
                            dialogBinding.etRollNo.text.toString()
                        )
                    )
                    dialog.dismiss()
                    studentAdapter.notifyDataSetChanged()
                }


            }
            dialog.show()
        }

    }


    override fun editClick(position: Int) {
        val dialogBinding1 = EditLayoutBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding1.root)
        dialogBinding1.etName.setText(dialogBinding1.etName.text.toString())

        dialogBinding1.btnEdit.setOnClickListener {
            if (dialogBinding1.etName.text.isEmpty()) {
                dialogBinding1.etName.error = "Enter your name"
            } else if (dialogBinding1.etRollNo.text.isEmpty()) {
                dialogBinding1.etRollNo.error = "enter your roll no"
            } else {
                StudentList.set(position, StudentModel(dialogBinding1.etName.text.toString(),dialogBinding1.etRollNo.text.toString()))

                studentAdapter.notifyDataSetChanged()
                dialog.dismiss()

            }

        }
        dialogBinding1.btnDelete.setOnClickListener {

            StudentList.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                dialog.dismiss()

        }
        dialog.show()
    }
}