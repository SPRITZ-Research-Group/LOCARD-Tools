package com.appboy.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.appboy.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.f.j;

public class AppboyFeedbackFragment extends Fragment {
    private static final String TAG = c.a(AppboyFeedbackFragment.class);
    private Button mCancelButton;
    private OnClickListener mCancelListener;
    private EditText mEmailEditText;
    private boolean mErrorMessageShown;
    private IFeedbackFinishedListener mFeedbackFinishedListener;
    private CheckBox mIsBugCheckBox;
    private EditText mMessageEditText;
    private int mOriginalSoftInputMode;
    private Button mSendButton;
    private TextWatcher mSendButtonWatcher;
    private OnClickListener mSendListener;

    public enum FeedbackResult {
        SUBMITTED,
        CANCELLED
    }

    public interface IFeedbackFinishedListener {
        String beforeFeedbackSubmitted(String str);

        void onFeedbackFinished(FeedbackResult feedbackResult);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSendButtonWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence sequence, int start, int before, int count) {
            }

            public void afterTextChanged(Editable sequence) {
                if (AppboyFeedbackFragment.this.mErrorMessageShown) {
                    AppboyFeedbackFragment.this.ensureSendButton();
                }
            }
        };
        this.mCancelListener = new OnClickListener() {
            public void onClick(View view) {
                AppboyFeedbackFragment.this.hideSoftKeyboard();
                if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                    AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.CANCELLED);
                }
                AppboyFeedbackFragment.this.clearData();
            }
        };
        this.mSendListener = new OnClickListener() {
            public void onClick(View view) {
                if (AppboyFeedbackFragment.this.ensureSendButton()) {
                    AppboyFeedbackFragment.this.hideSoftKeyboard();
                    boolean isBug = AppboyFeedbackFragment.this.mIsBugCheckBox.isChecked();
                    String message = AppboyFeedbackFragment.this.mMessageEditText.getText().toString();
                    String email = AppboyFeedbackFragment.this.mEmailEditText.getText().toString();
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        message = AppboyFeedbackFragment.this.mFeedbackFinishedListener.beforeFeedbackSubmitted(message);
                    }
                    a.a(AppboyFeedbackFragment.this.getActivity()).a(email, message, isBug);
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.SUBMITTED);
                    }
                    AppboyFeedbackFragment.this.clearData();
                    return;
                }
                AppboyFeedbackFragment.this.mErrorMessageShown = true;
            }
        };
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.com_appboy_feedback, container, false);
        this.mCancelButton = (Button) view.findViewById(R.id.com_appboy_feedback_cancel);
        this.mSendButton = (Button) view.findViewById(R.id.com_appboy_feedback_send);
        this.mIsBugCheckBox = (CheckBox) view.findViewById(R.id.com_appboy_feedback_is_bug);
        this.mMessageEditText = (EditText) view.findViewById(R.id.com_appboy_feedback_message);
        this.mEmailEditText = (EditText) view.findViewById(R.id.com_appboy_feedback_email);
        this.mMessageEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mCancelButton.setOnClickListener(this.mCancelListener);
        this.mSendButton.setOnClickListener(this.mSendListener);
        return view;
    }

    public void onResume() {
        super.onResume();
        a.a(getActivity()).b();
        Context activity = getActivity();
        Window window = activity.getWindow();
        this.mOriginalSoftInputMode = window.getAttributes().softInputMode;
        window.setSoftInputMode(16);
        a.a(activity).b();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMessageEditText.removeTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.removeTextChangedListener(this.mSendButtonWatcher);
    }

    private boolean validatedMessage() {
        boolean validMessage = (this.mMessageEditText.getText() == null || i.c(this.mMessageEditText.getText().toString())) ? false : true;
        if (validMessage) {
            this.mMessageEditText.setError(null);
        } else {
            displayMessageTextError(R.string.com_appboy_feedback_form_invalid_message);
        }
        return validMessage;
    }

    private boolean validatedEmail() {
        boolean validEmail;
        boolean blankEmail;
        if (this.mEmailEditText.getText() == null || i.c(this.mEmailEditText.getText().toString()) || !j.a(this.mEmailEditText.getText().toString())) {
            validEmail = false;
        } else {
            validEmail = true;
        }
        if (this.mEmailEditText.getText() == null || !i.c(this.mEmailEditText.getText().toString())) {
            blankEmail = false;
        } else {
            blankEmail = true;
        }
        if (validEmail) {
            this.mEmailEditText.setError(null);
        } else if (blankEmail) {
            displayEmailTextError(R.string.com_appboy_feedback_form_empty_email);
        } else {
            displayEmailTextError(R.string.com_appboy_feedback_form_invalid_email);
        }
        return validEmail;
    }

    private void displayEmailTextError(int resourceId) {
        if (getActivity() != null) {
            this.mEmailEditText.setError(getResources().getString(resourceId));
        } else {
            c.f(TAG, "Activity is null. Cannot set feedback form email error message");
        }
    }

    private void displayMessageTextError(int resourceId) {
        if (getActivity() != null) {
            this.mMessageEditText.setError(getResources().getString(resourceId));
        } else {
            c.f(TAG, "Activity is null. Cannot set feedback form message error.");
        }
    }

    private boolean ensureSendButton() {
        return validatedMessage() & validatedEmail();
    }

    private void clearData() {
        this.mEmailEditText.setText("");
        this.mMessageEditText.setText("");
        this.mIsBugCheckBox.setChecked(false);
        this.mErrorMessageShown = false;
        this.mEmailEditText.setError(null);
        this.mMessageEditText.setError(null);
    }

    private void hideSoftKeyboard() {
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(this.mOriginalSoftInputMode);
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
