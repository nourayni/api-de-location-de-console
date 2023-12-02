package com.consoletest.console.service.location;

import com.consoletest.console.dto.LocationRequest;
import com.consoletest.console.dto.LocationResponse;

public interface LocationService {
    LocationResponse createLocation(LocationRequest locationRequest);
}
