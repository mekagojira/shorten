import { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { config } from '../../shared/constant/config'
import axios from 'axios'
import { ResultResponse } from '../../shared/define/dto'

export const Link = () => {
  let { url } = useParams()

  const [loading, setLoading] = useState(true)
  const [err, setErr] = useState<string | null>(null)

  useEffect(() => {
    if (url) fetch(url)
  }, [url])

  const fetch = async (url: string) => {
    try {
      const res = await axios({
        url: config.apiHost + '/get',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        data: { url },
      })

      const responseBody = res.data as ResultResponse<{ link: string }>

      if (responseBody.code !== 'OK') {
        setErr(responseBody.message)
        return
      }
      if (!responseBody.data) {
        setErr('Something went wrong')
        return
      }

      window.location.href = responseBody.data.link
    } catch (error) {
      setErr('Something went wrong')
    } finally {
      setLoading(false)
    }
  }

  if (loading) return <div className="animate-bounce text-xl font-bold py-16">Redirecting ...</div>

  if (err) return <div className="text-xl font-bold py-8 text-red-400">{err}</div>

  return null
}
