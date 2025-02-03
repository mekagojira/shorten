export interface ResultResponse<T> {
  code: string
  data?: T
  message: string
}
