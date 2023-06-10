import SwiftUI
import shared

struct ContentView: View {
    
    let api = TheMovieDbApi()
    
    @State var upcoming: ApimodelsUpcomingMoviesDto? = nil
    
    @State var errorMessage: String? = nil

	var body: some View {
        VStack {
            if let errorMessage = errorMessage {
                Text(errorMessage)
            }
            if let list = upcoming?.results {
                List(list, id: \.self) { item in
                    Text(item.overview)
                }
            } else {
                EmptyView()
            }
        }.onAppear {
            getData()
        }
	}
    
    func getData() {
        api.getUpcoming { result, error in
            guard let result else { return }
            switch ApiResponseKs(result) {
            case .error(let errorResponse):
                switch ApiResponseErrorKs(errorResponse) {
                case .httpError(let http):
                    errorMessage = "Http error \(http.code)"
                case .networkError:
                    errorMessage = "network error"
                case .serializationError:
                    errorMessage = "serialisation error"
                case .unauthorizedException:
                    errorMessage = "unauthorized error"
                }
            case .success(let successResponse):
                upcoming = successResponse.body
            }
        }
    }
}
