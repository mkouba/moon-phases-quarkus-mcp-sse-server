package astro.moon;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record MoonPhaseRequest(@NotNull LocalDate date) {
}
