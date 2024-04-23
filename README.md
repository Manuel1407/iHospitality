
# Hospital API Service

## Overview

The Hospital API Service is designed to manage staff and patient profiles for a hospital. 
This API service provides functionalities to add and update staff; and patient profiles. 
The API is accessible internally to all staff members and requires staff UUID validation for access control.

## API Endpoints

1. **Add Staff Member**
    - `POST /api/v1/staff/create`
    - Request Body: `{"name": "John Doe"}`
    - Response: Staff profile with generated UUID
    - No authentication required.

2. **Update Staff Member Profile**
    - `PUT api/v1/staff/update/{uuid}`
    - Request Body: Updated staff profile
    - Requires validation - use 'staff-uuid' as header

3. **Fetch All Patient Profiles (Up to 2 years old)**
    - `GET /api/v1/patient/{age}`
    - Response: List of patient profiles
    - Requires validation - use 'staff-uuid' as header

4. **Download Patient Profile as CSV**
    - `GET /api/v1/patient/data/download`
    - Request Param: `{"name": "John Doe"}`
    - Requires validation - use 'staff-uuid' as header

5. **Delete Multiple Patient Profiles Between Date Range**
    - `DELETE /api/v1/patient/delete`
    - Request Params: `startDate`, `endDate`
    - Requires staff UUID validation

