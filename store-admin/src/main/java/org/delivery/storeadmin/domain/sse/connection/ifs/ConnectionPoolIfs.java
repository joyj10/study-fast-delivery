package org.delivery.storeadmin.domain.sse.connection.ifs;

public interface ConnectionPoolIfs<T,R> {
    void addSession(R session);

    R getSession(T uniqueKey);

    void onCompletionCallback(R session);
}
