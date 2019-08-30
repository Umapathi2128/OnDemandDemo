package practice.uma.com.ondemanddemo.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import practice.uma.com.ondemanddemo.R

class LoginActivity : AppCompatActivity(),View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1=findViewById<Button>(R.id.feature1)
        val btn2=findViewById<Button>(R.id.feature2)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.feature1 ->{
                btnClick("feature1","pilot.skyhop.com.feature1.MainActivity")
            }
            R.id.feature2 ->{
                btnClick("feature2","pilot.skyhop.com.feature2.MainActivity")
            }
            else->{
                Toast.makeText(this,"no clicked",Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * setting the module package and calling it....
     */
    private fun btnClick(moduleName:String,packageName:String){

        val splitInstallRequest=SplitInstallRequest.newBuilder().addModule(moduleName).build()

        val splitInstallManager=SplitInstallManagerFactory.create(applicationContext)

        splitInstallManager.startInstall(splitInstallRequest).addOnCompleteListener {
            val intent=Intent().setClassName(getPackageName(),packageName)
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Couldn't download feature2: " + it.message, Toast.LENGTH_LONG).show()
        }
    }
}
