package com.breadwallet.presenter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.breadwallet.R;
import com.breadwallet.presenter.activities.settings.BaseSettingsActivity;
import com.breadwallet.presenter.customviews.BRText;
import com.breadwallet.presenter.entities.BRSettingsItem;
import com.breadwallet.tools.adapter.SettingsAdapter;
import com.breadwallet.wallet.WalletsMaster;
import com.breadwallet.wallet.abstracts.BaseWalletManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 2/5/18.
 */

public class CurrencySettingsActivity extends BaseSettingsActivity {

    private BRText mTitle;
    private ListView listView;
    public List<BRSettingsItem> items;
    private static CurrencySettingsActivity app;


    @Override
    public int getLayoutId() {
        return R.layout.activity_currency_settings;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = findViewById(R.id.title);
        listView = findViewById(R.id.settings_list);

        final BaseWalletManager wm = WalletsMaster.getInstance(this).getCurrentWallet(this);

        mTitle.setText(String.format("%s %s", wm.getName(), CurrencySettingsActivity.this.getString(R.string.Button_settings)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (items == null)
            items = new ArrayList<>();
        items.clear();
        app = this;

        items.addAll(WalletsMaster.getInstance(this).getCurrentWallet(this).getSettingsConfiguration().mSettingList);
        View view = new View(this);
        listView.addFooterView(view, null, true);
        listView.addHeaderView(view, null, true);
        listView.setAdapter(new SettingsAdapter(this, R.layout.settings_list_item, items));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

}
