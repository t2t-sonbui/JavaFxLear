

package vn.mht.app.desktop.dagger.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A custom scoping annotation that specifies that the lifespan of a dependency be the same as that
 * of a Fragment.
 *
 * This is used to annotate dependencies that behave like a singleton within the lifespan of a
 * Fragment and child Fragments instead of the entire Application or Activity.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}