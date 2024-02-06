package com.streaming.streamingbackmvn.services.util;

import com.streaming.streamingbackmvn.dto.Range;

public class FileChunkUtil {

  public static int calculateChunkSize(Range range, long fileSize) {
    long startPosition = range.getRangeStart();
    long endPosition = range.getRangeEnd(fileSize);
    return (int) (endPosition - startPosition + 1);
  }
}
