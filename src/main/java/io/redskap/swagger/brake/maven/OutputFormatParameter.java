package io.redskap.swagger.brake.maven;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.maven.plugins.annotations.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputFormatParameter {
    @Parameter(name = "outputFormat")
    private String outputFormat;
}
