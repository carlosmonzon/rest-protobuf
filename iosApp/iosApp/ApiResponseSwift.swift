import shared

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
      return obj as shared.ApiResponseError<E>
    case .networkError:
      return shared.ApiResponseErrorNetworkError() as! shared.ApiResponseError<E>
    case .serializationError:
      return shared.ApiResponseErrorSerializationError() as! shared.ApiResponseError<E>
    case .unauthorizedException:
      return shared.ApiResponseErrorUnauthorizedException() as! shared.ApiResponseError<E>
    }
  }

  public init(_ obj: ApiResponseError<E>) {
    if let obj = obj as? shared.ApiResponseErrorHttpError<E> {
      self = .httpError(obj)
    } else if obj is shared.ApiResponseErrorNetworkError {
      self = .networkError
    } else if obj is shared.ApiResponseErrorSerializationError {
      self = .serializationError
    } else if obj is shared.ApiResponseErrorUnauthorizedException {
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
      return obj as! shared.ApiResponse<T, E>
    case .success(let obj):
      return obj as! shared.ApiResponse<T, E>
    }
  }

  public init(_ obj: ApiResponse<T, E>) {
    if let obj = obj as? shared.ApiResponseError<E> {
      self = .error(obj)
    } else if let obj = obj as? shared.ApiResponseSuccess<T> {
      self = .success(obj)
    } else {
      fatalError("ApiResponseKs not synchronized with ApiResponse class")
    }
  }

}
