package com.companyx.platform.portfolio.management.domain;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;

public class MessageContainer {


    public Span span;

    public Context context;
    public MessageContainer() {

    }

    public MessageContainer(Span span, Context context) {
        this.span = span;
        this.context = context;
    }

    public Span getSpan() {
        return span;
    }

    public void setSpan(Span span) {
        this.span = span;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
