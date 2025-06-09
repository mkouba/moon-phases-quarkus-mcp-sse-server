/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package astro.moon;

import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkiverse.mcp.server.ToolResponse;
import jakarta.inject.Inject;

import java.time.DateTimeException;
import java.time.LocalDate;

public class MoonPhasesMcpServer {

    @Inject
    MoonPhasesService moonPhasesService;

    @Tool(name = "current-moon-phase",
        description = "Provides the current moon phase")
    public TextContent currentMoonPhase() {
        return new TextContent(moonPhasesService.currentMoonPhase().toString());
    }

    @Tool(name = "moon-phase-at-date",
        description = "Provides the moon phase at a certain date (with a format of yyyy-MM-dd)")
    public ToolResponse moonPhaseAtDate(
        @ToolArg(name = "localDate",
            description = "The date for which the user wants to know the phase of the moon (in yyyy-MM-dd format)")
        String localDate) {
        try {
            LocalDate parsedLocalDate = LocalDate.parse(localDate);
            MoonPhase moonPhase = moonPhasesService.moonPhaseAtUnixTimestamp(parsedLocalDate.toEpochDay() * 86400);
            return ToolResponse.success(new TextContent(moonPhase.toString()));
        } catch (DateTimeException dte) {
            return ToolResponse.error("Not a valid date (yyyy-MM-dd): " + localDate);
        }
    }
}
