/*
   Copyright (c) 2014, The Linux Foundation. All Rights Reserved.
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of The Linux Foundation nor the names of its
      contributors may be used to endorse or promote products derived
      from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


package com.android.settings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class HotspotPreference extends Preference implements OnCheckedChangeListener {
    private Switch mSwitch;
    private TextView mSubSummary;
    private Context mContext;

    public HotspotPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public HotspotPreference(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.checkBoxPreferenceStyle);
        mContext = context;
    }

    public HotspotPreference(Context context) {
        this(context, null);
        mContext = context;
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        final LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.hotspot_checkbox, parent, false);
        mSubSummary = (TextView) view.findViewById(R.id.subsummary);
        mSubSummary.setVisibility(View.GONE);
        mSwitch = (Switch) view.findViewById(R.id.switchWidget);
        mSwitch.setOnCheckedChangeListener(this);
        return view;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        callChangeListener(isChecked);
    }

    public void setSummary(CharSequence summary) {
        if (mSubSummary != null) {
            mSubSummary.setText(summary);
            if (summary == null) {
                mSubSummary.setVisibility(View.GONE);
            } else {
                mSubSummary.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setChecked(boolean state) {
        if (mSwitch != null) {
            mSwitch.setOnCheckedChangeListener(null);
            mSwitch.setChecked(state);
            mSwitch.setOnCheckedChangeListener(this);
        }
    }
}
