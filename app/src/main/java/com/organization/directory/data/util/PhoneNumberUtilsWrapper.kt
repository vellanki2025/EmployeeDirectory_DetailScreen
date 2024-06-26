package com.organization.directory.data.util

import android.telephony.PhoneNumberUtils
import java.util.Locale

/**
 * Interface for defining the PhoneNumberUtils which provides methods to interact with the PhoneNumberUtils to format number.
 */
interface PhoneNumberUtilsWrapper {

    /**
     * Formats a phone number based on the device country locale
     *
     * @param number The phone number to be formatted.
     * @return formatted phone number.
     */
    fun formatNumber(number: String): String?
}

class PhoneNumberUtilsWrapperImpl : PhoneNumberUtilsWrapper {

    override fun formatNumber(number: String): String? {
        return PhoneNumberUtils.formatNumber(number, Locale.getDefault().country)
    }
}
