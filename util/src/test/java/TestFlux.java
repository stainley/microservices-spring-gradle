import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @author Stainley Lebron
 * @since 4/21/20.
 */

public class TestFlux {

    @Test
    public void TestFlux() {
        List<Integer> list = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .log()
                .subscribe(n -> list.add(n));

        assertThat(list, containsInAnyOrder(4, 8));
    }
}
