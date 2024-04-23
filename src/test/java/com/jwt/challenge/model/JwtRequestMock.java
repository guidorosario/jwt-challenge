package com.jwt.challenge.model;

public class JwtRequestMock {

    public static JwtRequest validJwtMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
    }

    public static JwtRequest invalidJwtMock(){
        return new JwtRequest("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
    }

    public static JwtRequest invalidJwtClaimExceedMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY");
    }

    public static JwtRequest invalidJwtDigitClaimNameMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs");
    }

    public static JwtRequest invalidJwtClaimRoleMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiVXNlciIsIlNlZWQiOiI3ODQxIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.zWpZCiFANBxuNoB1qml6jQidFoa6wSo7gN1c965UgVc");
    }

    public static JwtRequest invalidJwtClaimSeedNoPrimeMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MiIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.AWZjTIISO2Zka1Yv4ksQPdOhvRwt-UQRt2tMJpRof_A");
    }

    public static JwtRequest invalidJwtClaimNameSizeExceedMock(){
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJBIGJyaXNhIHN1YXZlIGFjYXJpY2lhIGFzIGZvbGhhcyBkYXMgw6Fydm9yZXMsIGNyaWFuZG8gdW1hIHNpbmZvbmlhIG5hdHVyYWwuIE8gcMO0ciBkbyBzb2wgcGludGEgbyBjw6l1IGNvbSB0b25zIGRvdXJhZG9zLCBlbnF1YW50byBww6Fzc2Fyb3Mgdm9hbSBlbSBkaXJlw6fDo28gYW8gaG9yaXpvbnRlLiBObyBjaMOjbywgZmxvcmVzIGRlc2Ficm9jaGFtIGVtIGNvcmVzIHZpYnJhbnRlcywgY29udHJhc3RhbmRvIGNvbSBhIHRlcnJhIGVzY3VyYS4gQSBjZW5hIGV2b2NhIHVtYSBzZW5zYcOnw6NvIGRlIHBheiBlIHRyYW5xdWlsaWRhZGUsIHVtIG1vbWVudG8gZGUgY29uZXjDo28gY29tIGEgbmF0dXJlemEuIn0.F4KuO1sP0ncYExDRRVJzWvdlMsEVnani9zQ4FjhjNVs");
    }

    public static JwtRequest invalidJwt2ClaimMock() {
        return new JwtRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJOYW1lIjoiVG9uaW5obyBBcmF1am8ifQ.Incs7uhGp266QhwxZ22uzFiHC1W4NWuPOJT3rWX6A7g");
    }



}
