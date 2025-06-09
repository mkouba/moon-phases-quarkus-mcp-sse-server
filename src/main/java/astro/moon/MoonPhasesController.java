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

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/moon-phases")
public class MoonPhasesController {

    @Inject
    MoonPhasesService moonPhasesService;

    @GET
    @Path("/current")
    @Produces(MediaType.APPLICATION_JSON)
    public MoonPhase currentMoonPhase() {
        return moonPhasesService.currentMoonPhase();
    }

    @GET
    @Path("/at-date/{localDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public MoonPhase currentMoonPhase(@PathParam("localDate") String localDateString) {
        LocalDate localDate = LocalDate.parse(localDateString);
        long unixTimestamp = localDate.toEpochDay() * 86400;
        return moonPhasesService.moonPhaseAtUnixTimestamp(unixTimestamp);
    }
}
