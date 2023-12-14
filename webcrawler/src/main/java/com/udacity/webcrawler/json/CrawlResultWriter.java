package com.udacity.webcrawler.json;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utility class to write a {@link CrawlResult} to file.
 */
public final class CrawlResultWriter {
  private final CrawlResult result;
  static final ObjectMapper objm = new ObjectMapper();

  /**
   * Creates a new {@link CrawlResultWriter} that will write the given {@link CrawlResult}.
   */
  public CrawlResultWriter(CrawlResult result) {
    this.result = Objects.requireNonNull(result);
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Path}.
   *
   * <p>
   * If a file already exists at the path, the existing file should not be deleted; new data should
   * be appended to it.
   *
   * @param path the file path where the crawl result data should be written.
   */
  public void write(Path path) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(path);
    // Fill in this method.
    try( Writer wr = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
          StandardOpenOption.APPEND)) {
      write(wr);
    } catch (Exception e) {
      // handle exception
      e.printStackTrace();
    }
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Writer}.
   *
   * @param writer the destination where the crawl result data should be written.
   */
  public void write(Writer writer) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(writer);
    objm.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    objm.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    objm.enable(SerializationFeature.INDENT_OUTPUT);
    // Fill in this method.
    try {
      objm.writeValue(writer, result);
    } catch (Exception e) {
      // handle exception
      e.printStackTrace();
    }
  }
}
