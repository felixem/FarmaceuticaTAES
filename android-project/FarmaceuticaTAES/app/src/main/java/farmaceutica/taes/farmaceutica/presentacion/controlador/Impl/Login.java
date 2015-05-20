package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AlertaDialogo;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisitador;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 *
 */
public class Login extends FragmentActivity {

    private Button btn_login;
    private Button btn_cancel;
    private Button btn_inicio_rapido;
    private EditText et_usuario, et_password;
    private FachadaVisitador fachadaVisitador;
    private RadioButton rb_offline;

    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);


        et_usuario = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);

        fachadaVisitador = new FachadaVisitador();




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visitador visi = null;

                 String user = et_usuario.getText().toString();
                 String pass = et_password.getText().toString();
                try {
                    visi = fachadaVisitador.login(getApplicationContext(), user, pass);
                }catch (Exception e)
                {

                }

                if(visi==null)
                {
                    AlertaDialogo ad = new AlertaDialogo();
                    ad.setMensaje("El código/email del usuario o la contraseña no son correctos");
                    ad.setTitulo("Inicio de sesión denegado");
                    ad.setBoton1("OK");
                    ad.setFlags(true);
                    ad.show(getSupportFragmentManager(), "FragmentAlert");

                }else
                {
                    MySession session = (MySession) getApplication();
                    session.setVisitador(visi);

                    Intent intent = new Intent(Login.this, MainActivity.class);

                    finish();
                    startActivity(intent);
                }
            }
        });



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }

}
