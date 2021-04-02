export class SkylabUser {
  payload: Record<string, unknown>;
  constructor() {
    this.payload = {};
  }
  init(
    deviceId?: string,
    userId?: string,
    version?: string,
    country?: string,
    region?: string,
    dma?: string,
    city?: string,
    language?: string,
    platform?: string,
    os?: string,
    deviceFamily?: string,
    deviceType?: string,
    deviceManufacturer?: string,
    deviceModel?: string,
    carrier?: string,
    userProperties?: { [key: string]: string }
  ): Record<string, unknown> {
    this.payload.deviceId = deviceId;
    this.payload.userId = userId;
    this.payload.version = version;
    this.payload.country = country;
    this.payload.region = region;
    this.payload.dma = dma;
    this.payload.city = city;
    this.payload.language = language;
    this.payload.platform = platform;
    this.payload.os = os;
    this.payload.device_family = deviceFamily;
    this.payload.device_type = deviceType;
    this.payload.device_manufacturer = deviceManufacturer;
    this.payload.device_model = deviceModel;
    this.payload.carrier = carrier;
    this.payload.user_properties = userProperties;
    return this.payload;
  }
}
