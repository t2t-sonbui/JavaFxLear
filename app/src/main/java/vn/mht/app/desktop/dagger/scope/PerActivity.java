
package vn.mht.app.desktop.dagger.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

