package io.github.pps5.sprint.property

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NavControllerProperty(
    private val navControllerProvider: (Fragment) -> NavController
) : ReadOnlyProperty<Fragment, NavController> {
    private var navController: NavController? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): NavController {
        val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyView(lifecycleOwner: LifecycleOwner) {
                navController = null
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        }
        if (navController == null) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(observer)
            navController = navControllerProvider(thisRef)
        }
        return navController!!
    }

}

@Suppress("unused")
fun Fragment.childNavController(@IdRes navHostFragmentId: Int): NavControllerProperty {
    return NavControllerProperty { fragment ->
        val navHost = fragment
            .childFragmentManager
            .findFragmentById(navHostFragmentId) as NavHostFragment
        navHost.navController
    }
}

@Suppress("unused")
fun Fragment.navController(): ReadOnlyProperty<Fragment, NavController> {
    return NavControllerProperty { fragment -> fragment.findNavController() }
}

