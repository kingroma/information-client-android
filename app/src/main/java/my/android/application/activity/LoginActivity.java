package my.android.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import api.service.UserService;
import my.android.application.config.DefaultConfig;

public class LoginActivity extends AppCompatActivity {

	private UserService userService = null ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        userService = new UserService();
        
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    
    private final String KEY_TOKEN = "TOKEN";

    private void login(String userId, String userPw) {
    	String token = userService.getToken(userId,userPw);
    	
    	if ( token != null && !token.isEmpty() ) {
    		DefaultConfig.TOKEN = token ;

            SharedPreferences sf = getSharedPreferences(DefaultConfig.DEFAULT_PREFERENCES, 0);

            SharedPreferences.Editor editor = sf.edit();
            editor.putString(KEY_TOKEN, token);
            editor.commit();
    	} else { 
    		// login fail 
    	}
    }
    
    private String getInternalStorageToken(){
        SharedPreferences sf = getSharedPreferences(DefaultConfig.DEFAULT_PREFERENCES, 0);
        String token  = sf.getString(KEY_TOKEN, "");

        return token ; 
    }
    
}
