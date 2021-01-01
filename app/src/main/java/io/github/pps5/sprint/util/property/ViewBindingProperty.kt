package io.github.pps5.sprint.util.property

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingProperty<T : ViewBinding>(
    private val binder: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyView(lifecycleOwner: LifecycleOwner) {
                binding = null
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        }
        if (binding == null) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(observer)
            binding = binder(thisRef.requireView())
        }
        return binding!!
    }

}

@Suppress("unused")
fun <T : ViewBinding> Fragment.viewBinding(binder: (View) -> T): ViewBindingProperty<T> {
    return ViewBindingProperty(binder)
}
