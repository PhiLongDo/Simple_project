package com.dplong.sample_project.data.pref
// https://github.com/AndroidLab/PreferencesDelegate/tree/master
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

/**
 * Preference delegate.
 * @param preferences Instance of shared preferences.
 * @param name Preference name.
 * @param defValue Default value.
 * @param onDifficultTypeTransform Transforms a complex data type into a primitive one.
 * @param onPrimitiveTypeTransform Transforms a primitive data type into a complex one.
 * @param prefFlow Flow with preference.
 */
class PreferencesDelegate<DIFFICULT_TYPE, SIMPLE_TYPE>(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defValue: DIFFICULT_TYPE,
    private val onDifficultTypeTransform: ((value: DIFFICULT_TYPE) -> SIMPLE_TYPE)? = null,
    private val onPrimitiveTypeTransform: ((value: SIMPLE_TYPE) -> DIFFICULT_TYPE)? = null,
    private val prefFlow: MutableSharedFlow<DIFFICULT_TYPE>? = null,
) {
    private fun callNotImplementedError(value: Any?) {
        throw NotImplementedError("Not implemented for ${value?.javaClass}")
    }

    private val delegateScope = CoroutineScope(Dispatchers.IO)

    init {
        delegateScope.launch {
            prefFlow?.emit(getValueHandler())
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        getValueHandler()

    operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: DIFFICULT_TYPE,
    ) = setValueHandler(value)

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun getValueHandler(): DIFFICULT_TYPE {
        fun <R> getValue(defValue: R): SIMPLE_TYPE {
            with(preferences) {
                return when (defValue) {
                    is Boolean -> getBoolean(name, defValue)
                    is String -> getString(name, defValue)
                    is Float -> getFloat(name, defValue)
                    is Int -> getInt(name, defValue)
                    is Long -> getLong(name, defValue)
                    is Set<*> -> getStringSet(name, defValue as Set<String>)
                    else -> callNotImplementedError(defValue)
                } as SIMPLE_TYPE
            }
        }
        return if (onDifficultTypeTransform == null || onPrimitiveTypeTransform == null) {
            getValue(defValue) as DIFFICULT_TYPE
        } else {
            onPrimitiveTypeTransform.invoke(
                getValue(onDifficultTypeTransform.invoke(defValue))
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setValueHandler(value: DIFFICULT_TYPE) {
        delegateScope.launch {
            fun <R> setValue(value: R) {
                with(preferences.edit()) {
                    when (value) {
                        is Boolean -> putBoolean(name, value)
                        is String -> putString(name, value)
                        is Float -> putFloat(name, value)
                        is Int -> putInt(name, value)
                        is Long -> putLong(name, value)
                        is Set<*> -> putStringSet(name, value as Set<String>)
                        else -> callNotImplementedError(value)
                    }
                    apply()
                }
            }
            if (onDifficultTypeTransform == null) {
                setValue(value)
            } else {
                setValue(onDifficultTypeTransform.invoke(value))
            }
            prefFlow?.emit(value)
        }
    }
}