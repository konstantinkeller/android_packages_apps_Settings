package com.android.settings.pcf;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

public class StatusBarBattery extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String PREF_BATT_ICON = "battery_icon_list";

    ListPreference mBatteryIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_statusbar_battery);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_statusbar_battery);

        mBatteryIcon = (ListPreference) findPreference(PREF_BATT_ICON);
        mBatteryIcon.setOnPreferenceChangeListener(this);
        mBatteryIcon.setValue((Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.STATUSBAR_BATTERY_ICON,
                0))
                + "");
    }

    @Override
    public boolean OnPreferenceChange(Preference preference, Object newValue) {
        if (preference == mBatteryIcon) {
            int val = Integer.parseInt((String) newValue);
            return Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_ICON, val);
        }
        return false;
    }
}
