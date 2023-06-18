import DataKit

/**
 * selector: ClassContext/rest-protobuf:shared/com/cmonzon/data/ApiResponse.Error */
public enum ApiResponseErrorKs<E : AnyObject> {

  case httpError(ApiResponseErrorHttpError<E>)
  case networkError
  case serializationError
  case unauthorizedException

  public var sealed: ApiResponseError<E> {
    switch self {
    case .httpError(let obj):
      return obj as DataKit.ApiResponseError<E>
    case .networkError:
      return DataKit.ApiResponseErrorNetworkError() as! DataKit.ApiResponseError<E>
    case .serializationError:
      return DataKit.ApiResponseErrorSerializationError() as! DataKit.ApiResponseError<E>
    case .unauthorizedException:
      return DataKit.ApiResponseErrorUnauthorizedException() as! DataKit.ApiResponseError<E>
    }
  }

  public init(_ obj: ApiResponseError<E>) {
    if let obj = obj as? DataKit.ApiResponseErrorHttpError<E> {
      self = .httpError(obj)
    } else if obj is DataKit.ApiResponseErrorNetworkError {
      self = .networkError
    } else if obj is DataKit.ApiResponseErrorSerializationError {
      self = .serializationError
    } else if obj is DataKit.ApiResponseErrorUnauthorizedException {
      self = .unauthorizedException
    } else {
      fatalError("ApiResponseErrorKs not synchronized with ApiResponseError class")
    }
  }

}

/**
 * selector: ClassContext/rest-protobuf:shared/com/cmonzon/data/ApiResponse */
public enum ApiResponseKs<T : AnyObject, E : AnyObject> {

  case error(ApiResponseError<E>)
  case success(ApiResponseSuccess<T>)

  public var sealed: ApiResponse<T, E> {
    switch self {
    case .error(let obj):
      return obj as! DataKit.ApiResponse<T, E>
    case .success(let obj):
      return obj as! DataKit.ApiResponse<T, E>
    }
  }

  public init(_ obj: ApiResponse<T, E>) {
    if let obj = obj as? DataKit.ApiResponseError<E> {
      self = .error(obj)
    } else if let obj = obj as? DataKit.ApiResponseSuccess<T> {
      self = .success(obj)
    } else {
      fatalError("ApiResponseKs not synchronized with ApiResponse class")
    }
  }

}
