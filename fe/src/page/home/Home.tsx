import { useState } from 'react'
import axios from 'axios'
import { config } from '../../shared/constant/config'
import { ResultResponse } from '../../shared/define/dto'
import { Link } from 'react-router'

export const Home = () => {
  const [request, setRequest] = useState<{ url: string }>({ url: '' })
  const [err, setErr] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)
  const [link, setLink] = useState<string | null>(null)

  const onSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault()

    if (loading) return

    const urlRegex = new RegExp('^(http|https)://')
    if (!urlRegex.test(request.url)) {
      setErr('Invalid URL')
      return
    }

    setLoading(true)

    try {
      const res = await axios({
        url: config.apiHost + '/create',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        data: request,
      })

      const responseBody = res.data as ResultResponse<{ link: string }>
      setLoading(false)
      if (responseBody.code !== 'OK') {
        setErr(responseBody.message)
        return
      }
      if (!responseBody.data) {
        setErr('Something went wrong')
        return
      }
      setLink(config.baseUrl + '/' + responseBody.data.link)
    } catch (e) {
      setLoading(false)
      setErr('Something went wrong')
    }
  }

  return (
    <div>
      <form aria-disabled={loading} onSubmit={onSubmit} className="px-3 md:px-16 py-4 md:py-8 border border-bg-2 shadow-xl rounded-lg">
        <div className="text-lg">Paste the URL to be shortened</div>
        <div className="pb-4" />
        <div className="flex flex-col">
          <input
            value={request.url}
            onChange={e => {
              setErr(null)
              setRequest({ ...request, url: e.target.value })
            }}
            type="text"
            name="link"
            placeholder="Paste your link"
            className="transition-all w-full rounded-lg shadow focus:shadow-xl text-slate-100 px-3 md:px-8 py-4 bg-bg-2"
          />
          {err && (
            <div className="pt-2">
              <div className="text-red-400 font-bold">{err}</div>
            </div>
          )}
        </div>
        <div className="pb-4" />
        <div className="flex justify-center items-center">
          <button disabled={loading} type="submit" className="transition-all cursor-pointer rounded-lg shadow focus:shadow-xl text-slate-100 px-3 md:px-8 py-4 bg-indigo-600 font-bold text-sm uppercase">
            {loading ? '...' : 'Shorten URL'}
          </button>
        </div>
      </form>
      <div className="pb-4" />
      {link && (
        <div className="text-lg flex items-baseline">
          <span>Your short link: </span>
          <span className="px-1" />
          <Link to={link} target="_blank">
            <span className="text-indigo-400 underline">{link}</span>
          </Link>
        </div>
      )}
    </div>
  )
}
