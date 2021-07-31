package com.github.prgrms.configures.web;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.google.common.base.Preconditions.checkArgument;

public class SimplePageRequest implements Pageable {

    private final long offset;

    private final int size;

    public SimplePageRequest() {
        this(0, 5);
    }

    public SimplePageRequest(long offset, int size) {
        checkArgument(offset >= 0, "offset must be greater or equals to zero");
        checkArgument(size >= 1, "size must be greater than zero");

        this.offset = offset;
        this.size = size;
    }

    public SimplePageRequest(String offsetStr, String sizeStr) {
        //number format exception 예외처리 필요
        long offset = 0L;
        int size = 5;
        
        if (offsetStr != null) {
            long tempOffset = Long.parseLong(offsetStr);
            if (tempOffset >= 0 && tempOffset <= Long.MAX_VALUE) {
                offset = tempOffset;
            }
        }

        if (sizeStr != null) {
            int tempSize = Integer.parseInt(sizeStr);
            if (tempSize >= 0 && tempSize <= 5) {
                size = tempSize;
            }
        }
        this.offset = offset;
        this.size = size;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("offset", offset)
                .append("size", size)
                .toString();
    }

}