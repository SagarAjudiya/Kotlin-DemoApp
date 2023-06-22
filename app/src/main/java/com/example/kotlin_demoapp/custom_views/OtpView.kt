package com.example.kotlin_demoapp.custom_views

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.kotlin_demoapp.R

class OtpView : LinearLayout {

    private lateinit var editText: EditText
    private lateinit var backDrawable: GradientDrawable
    private val textCount: Int by lazy { 1 }
    private var otpCount = 1
    private var customTextColor = 0
    private var backgroundColor = 0
    private var borderWidth = 0
    private var borderColor = 0
    private var customPadding = 0
    private var customMargin = 0
    private val otpList = ArrayList<EditText>()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttrFromXML(attrs, context)
        initOTP(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrFromXML(attrs, context)
        initOTP(context, attrs)
    }

    private fun getAttrFromXML(attrs: AttributeSet?, context: Context) {
        val attrData = context.obtainStyledAttributes(attrs, R.styleable.OtpView)
        otpCount = attrData.getInt(R.styleable.OtpView_otpCount, 1)
        customTextColor = attrData.getColor(
            R.styleable.OtpView_custom_text_color,
            context.getColor(R.color.black)
        )
        backgroundColor =
            attrData.getColor(R.styleable.OtpView_backgroundColor, context.getColor(R.color.white))
        borderWidth = attrData.getDimension(R.styleable.OtpView_borderWidth, 2f).toInt()
        customPadding = attrData.getDimension(R.styleable.OtpView_custom_padding, 5f).toInt()
        customMargin = attrData.getDimension(R.styleable.OtpView_custom_margin, 5f).toInt()
        borderColor =
            attrData.getColor(R.styleable.OtpView_custom_text_color, context.getColor(R.color.grey))
        attrData.recycle()
    }

    private fun initOTP(context: Context, attrs: AttributeSet?) {
        for (i in 1..otpCount) {
            addEditText()
        }
        otpList.forEachIndexed { index, editText ->
            if (index < otpList.count() - 1 && index > 0) {
                editText.addTextChangedListener(
                    CustomTextWatcher(
                        editText,
                        otpList[index + 1]
                    )
                )
                editText.setOnKeyListener(CustomKeyEvent(editText, otpList[index - 1]))
            } else if (index == 0) {
                editText.addTextChangedListener(
                    CustomTextWatcher(
                        editText,
                        otpList[1]
                    )
                )
            } else {
                editText.setOnKeyListener(CustomKeyEvent(editText, otpList[index - 1]))
                editText.addTextChangedListener(CustomTextWatcher(editText, null) {
                    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                        windowToken,
                        0
                    )
                })
            }
        }
    }

    private fun addEditText() {
        editText = EditText(context).apply {
            layoutParams =
                LayoutParams(200, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(customMargin, 0, customMargin, 0)
                }
            gravity = Gravity.CENTER
            setPadding(customPadding, customPadding, customPadding, customPadding)
            setTextColor(customTextColor)
            setSelectAllOnFocus(true)
            inputType = InputType.TYPE_CLASS_NUMBER
            filters += LengthFilter(1)
            background = AppCompatResources.getDrawable(context, R.drawable.cornered_border)
        }
        backDrawable = editText.background as GradientDrawable
        backDrawable.apply {
            mutate()
            setColor(backgroundColor)
            setStroke(borderWidth, borderColor)
        }
        otpList.add(editText)
        addView(editText)
    }
}

class CustomTextWatcher(
    private val currentView: View,
    private val nextView: View?,
    private val completion: () -> Unit = {}
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (count == 1) {
            if (nextView != null) {
                currentView.clearFocus()
                nextView.requestFocus()
            } else {
                currentView.clearFocus()
                completion()
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}
}

class CustomKeyEvent(private val currentView: EditText, private val previousView: EditText?) :
    View.OnKeyListener {
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null && event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && previousView != null && currentView.text.isEmpty()) {
            previousView.text = null
            previousView.requestFocus()
            return true
        }
        return false
    }
}