package cr.ac.utn.census

import Controller.PersonController
import Entity.Person
import Entity.Province
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PersonActivity : AppCompatActivity() {
    lateinit var txtId: EditText
    lateinit var txtName: EditText
    lateinit var txtFLastName: EditText
    lateinit var txtSLastName: EditText
    lateinit var txtPhone: EditText
    lateinit var txtEmail: EditText
    lateinit var txtProvince: EditText
    lateinit var txtState: EditText
    lateinit var txtDistrict: EditText
    lateinit var txtAddress: EditText
    lateinit var txtBirthDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_person)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtId= findViewById<EditText>(R.id.txtId_person)
        txtName= findViewById<EditText>(R.id.txtName_person)
        txtFLastName= findViewById<EditText>(R.id.txtFLastName_person)
        txtSLastName= findViewById<EditText>(R.id.txtSLastName_person)
        txtBirthDate= findViewById<EditText>(R.id.txtBirthdate_person)
        txtPhone= findViewById<EditText>(R.id.txtPhone_person)
        txtEmail= findViewById<EditText>(R.id.txtEmail_person)
        txtProvince= findViewById<EditText>(R.id.txtProvince_person)
        txtState= findViewById<EditText>(R.id.txtState_person)
        txtDistrict= findViewById<EditText>(R.id.txtDistrict_person)
        txtAddress= findViewById<EditText>(R.id.txtAddress_person)

        var btnSave = findViewById<Button>(R.id.btnSave_person)
        btnSave.setOnClickListener (View.OnClickListener{view ->
            savePerson()
        })
    }
    fun validationData(): Boolean{
        return true
    }

    fun savePerson(){
        try {
            val person = Person()
            person.ID = txtId.text.toString()
            person.Name = txtId.text.toString()
            person.FLastName = txtId.text.toString()
            person.SLastName = txtId.text.toString()
            //person.BirthDate = txtBirthDate.text.toString()
            person.Email = txtId.text.toString()
            person.Phone = txtPhone.text.toString().toInt()
            //person.Province = Province()
            person.State = txtState.text.toString()
            person.District = txtDistrict.text.toString()
            person.Address = txtAddress.text.toString()

            if (validationData()){
                var personController = PersonController(this)
                personController.addPerson(person)
            }else{

            }
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }
}