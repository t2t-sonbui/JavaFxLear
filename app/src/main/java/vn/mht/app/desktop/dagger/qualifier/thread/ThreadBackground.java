
package vn.mht.app.desktop.dagger.qualifier.thread;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadBackground {
}
