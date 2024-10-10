package com.example.tasklist

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasklist.app.utils.setupDialog
import com.example.tasklist.app.utils.validateEditText
import com.example.tasklist.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val mainBinding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val addTaskDialog:Dialog by lazy{
        Dialog(this,R.style.DialogCustomTheme).apply{
            setupDialog(R.layout.add_task_dialog)
        }
    }

    private val updateTaskDialog:Dialog by lazy{
        Dialog(this,R.style.DialogCustomTheme).apply{
            setupDialog(R.layout.update_task_dialog)
        }
    }

    private val loadingDialog:Dialog by lazy{
        Dialog(this,R.style.DialogCustomTheme).apply{
            setupDialog(R.layout.loading_dialog)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        //add task start
        val addCloseImg=addTaskDialog.findViewById<ImageView>(R.id.closeImg)
        addCloseImg.setOnClickListener{addTaskDialog.dismiss()}

        val addEDTitle =addTaskDialog. findViewById<TextInputEditText>(R.id.edTaskTitle)
        val addEDTitleL= addTaskDialog.findViewById<TextInputLayout>(R.id.edTaskTitleL)

        addEDTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
               validateEditText(addEDTitle, addEDTitleL)
            }
        })

        val addEDDesc = updateTaskDialog.findViewById<TextInputEditText>(R.id.edTaskDesc)
        val addEDDescL= updateTaskDialog.findViewById<TextInputLayout>(R.id.edTaskDescL)

        addEDDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addEDDesc, addEDDescL)
            }
        })


        mainBinding.addTaskFABtn.setOnClickListener{
            addTaskDialog.show()
        }

        val saveTaskBtn=addTaskDialog.findViewById<Button>(R.id.saveTaskBtn)
        saveTaskBtn.setOnClickListener {
          if( validateEditText(addEDTitle, addEDTitleL)
              &&  validateEditText(addEDDesc, addEDDescL)
              )  {
                addTaskDialog.dismiss()
                Toast.makeText(this,"validated!!",Toast.LENGTH_LONG).show()
              loadingDialog.show()
          }
        }


        //Add task end


        //update Task start
        val updateEDTitle =updateTaskDialog. findViewById<TextInputEditText>(R.id.edTaskTitle)
        val updateEDTitleL= updateTaskDialog.findViewById<TextInputLayout>(R.id.edTaskTitleL)

       updateEDTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateEDTitle, updateEDTitleL)
            }
        })

        val updateETDesc = updateTaskDialog.findViewById<TextInputEditText>(R.id.edTaskDesc)
        val updateETDescL= updateTaskDialog.findViewById<TextInputLayout>(R.id.edTaskDescL)

        updateETDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateETDesc, updateETDescL)
            }
        })

        val updateCloseImg=updateTaskDialog.findViewById<ImageView>(R.id.closeImg)
        updateCloseImg.setOnClickListener{updateTaskDialog.dismiss()}


        val updateTaskBtn=updateTaskDialog.findViewById<Button>(R.id.updateTaskBtn)
        updateTaskBtn.setOnClickListener {
            if( validateEditText(updateEDTitle, updateEDTitleL)
                &&   validateEditText(updateETDesc, updateETDescL)
            )  {
                updateTaskDialog.dismiss()
                Toast.makeText(this,"validated!!",Toast.LENGTH_LONG).show()
                loadingDialog.show()
            }
        }


        //update task end


    }
}