package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException.ErrorType;
import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.entitiy.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.SocketPolicy;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class ProxerCallTest extends ProxerTest {

    @Test
    public void testTimeoutError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json"))
                .setSocketPolicy(SocketPolicy.NO_RESPONSE));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getErrorType() == ErrorType.TIMEOUT,
                        "TIMEOUT ErrorType"));
    }

    @Test
    public void testIOError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")).setResponseCode(404));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getErrorType() == ErrorType.IO,
                        "IO ErrorType"));
    }

    @Test
    public void testInvalidEncodingError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace(":", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getErrorType() == ErrorType.IO,
                        "IO ErrorType"));
    }

    @Test
    public void testInvalidDataError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace("256", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getErrorType() == ErrorType.PARSING,
                        "PARSING ErrorType"));
    }

    @Test
    public void testServerError() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("conferences_error.json")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.messenger().conferences().build().execute())
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getErrorType() == ErrorType.SERVER,
                        "SERVER ErrorType"))
                .has(new Condition<Throwable>(e -> ((ProxerException) e).getServerErrorType() == ServerErrorType.MESSAGES_LOGIN_REQUIRED,
                        "MESSAGES_LOGIN_REQUIRED ServerErrorType"))
                .has(new Condition<Throwable>(e -> e.getMessage().equals("Du bist nicht eingeloggt."),
                        "Message is equal"));
    }

    @Test(timeout = 1000L)
    public void testEnqueue() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.notifications().news().build().enqueue(result -> lock.countDown(),
                exception -> {
                    // Failed. The lock will never be counted down and timeout.
                });

        lock.await();
    }

    @Test
    public void testEnqueueError() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")).setResponseCode(404));

        api.notifications().news().build().enqueue(result -> {
                    // Failed. The lock will never be counted down and timeout.
                },
                exception -> {
                    if (exception.getErrorType() == ErrorType.IO) {
                        lock.countDown();
                    }

                    // Failed: Not the exception we want. The lock will never be counted down and timeout.
                });

        lock.await();
    }

    @Test
    public void testIsExecuted() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        assertThat(call.isExecuted()).isTrue();
    }

    @Test(timeout = 1000L)
    public void testCancel() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> {
            if (exception.getErrorType() == ErrorType.IO) {
                lock.countDown();
            }

            // Failed: Not the exception we want. The lock will never be counted down and timeout.
        });

        call.cancel();
        lock.await();
    }

    @Test(timeout = 1000L)
    public void testIsCanceled() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> lock.countDown());

        call.cancel();
        lock.await();

        assertThat(call.isCanceled()).isTrue();
    }

    @Test
    public void testClone() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        assertThat(call.clone().execute()).isNotNull();
    }

    @Test
    public void testRequest() {
        assertThat(api.notifications().news().build().request()).isNotNull();
    }
}
