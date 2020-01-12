package net.jonathangiles.android.appconfig;

import android.os.Bundle;

import com.azure.data.appconfiguration.ConfigurationClient;
import com.azure.data.appconfiguration.ConfigurationClientBuilder;
import com.azure.data.appconfiguration.models.ConfigurationSetting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = findViewById(R.id.appConfigBtn);
        btn.setOnClickListener(view -> doAppConfigTest());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doAppConfigTest() {
        System.out.println("Clicked!");

        // The connection string value can be obtained by going to your App Configuration instance in the Azure portal
        // and navigating to "Access Keys" page under the "Settings" section.
        String connectionString = "<CONNECTION_STRING_HERE>";

        final ConfigurationClient client = new ConfigurationClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Name of the key to add to the configuration service.
        final String key = "hello";
        final String value = "world";

        System.out.println("Beginning of synchronous sample...");

        ConfigurationSetting setSetting = client.setConfigurationSetting(key, null, value);
        System.out.printf(String.format("[SetConfigurationSetting] Key: %s, Value: %s\n", setSetting.getKey(), setSetting.getValue()));

        ConfigurationSetting getSetting = client.getConfigurationSetting(key, null, null);
        System.out.printf(String.format("[GetConfigurationSetting] Key: %s, Value: %s\n", getSetting.getKey(), getSetting.getValue()));

        ConfigurationSetting deleteSetting = client.deleteConfigurationSetting(key, null);
        System.out.printf(String.format("[DeleteConfigurationSetting] Key: %s, Value: %s\n", deleteSetting.getKey(), deleteSetting.getValue()));
    }
}
