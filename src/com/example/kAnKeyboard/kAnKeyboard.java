/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kAnKeyboard;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.EditorInfo;

public class kAnKeyboard extends InputMethodService
       implements KeyboardView.OnKeyboardActionListener
{
    public static String TAG = "kAnKeyboard";
    SurfaceView  mView;
    KeyboardView mInputView;
    Keyboard     mQwertyKeyboard;

    /**/
    public void    onKey(int primaryCode, int[] keyCodes)
    {
    }
    public void    onPress(int primaryCode)
    {
    }
    public void    onRelease(int primaryCode)
    {
    }
    public void    onText(CharSequence text)
    {
    }
    public void    swipeDown()
    {
    }
    public void    swipeLeft()
    {
    }
    public void    swipeRight()
    {
    }
    public void    swipeUp()
    {
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate()
    {
      super.onCreate();

      mView = new KeyboardView(getApplication());
    }

    @Override
    public View onCreateInputView() {
        mInputView = (LatinKeyboardView) getLayoutInflater().inflate(
                R.layout.input, null);
        mInputView.setOnKeyboardActionListener(this);
        mInputView.setKeyboard(mQwertyKeyboard);
        return mInputView;
    }

    @Override
    public View onCreateCandidatesView() {
        mCandidateView = new CandidateView(this);
        mCandidateView.setService(this);
        return mCandidateView;
    }

    @Override public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        // Apply the selected keyboard to the input view.
        mInputView.setKeyboard(mCurKeyboard);
        mInputView.closing();
        final InputMethodSubtype subtype = mInputMethodManager.getCurrentInputMethodSubtype();
        mInputView.setSubtypeOnSpaceKey(subtype);
    }

    @Override public void onFinishInput() {
        super.onFinishInput();

        // Clear current composing text and candidates.
        mComposing.setLength(0);
        updateCandidates();

        // We only hide the candidates window when finishing input on
        // a particular editor, to avoid popping the underlying application
        // up and down if the user is entering text into the bottom of
        // its window.
        setCandidatesViewShown(false);

        mCurKeyboard = mQwertyKeyboard;
        if (mInputView != null) {
            mInputView.closing();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
       super.onConfigurationChanged(newConfig);

       // Checks the orientation of the screen
       if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
         Log.v(TAG, "ORIENTATION_LANDSCAPE");
       }
       else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
         Log.v(TAG, "ORIENTATION_PORTRAIT");
       }
    }

    //@Override
    public boolean onTouchEvent(MotionEvent me)
    {
    	switch(me.getAction())
    	{
    	case MotionEvent.ACTION_MOVE:
    		break;
    	case MotionEvent.ACTION_DOWN:
    		break;
    	case MotionEvent.ACTION_UP:
    		break;
    	}

    	return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

    	return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {

    	return true;
    }
}
