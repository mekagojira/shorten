import { Route } from 'react-router'
import { Routes } from 'react-router'
import { Home } from './page/home/Home'
import { Link } from './page/link/Link'
import { Container } from './Container'

function App() {
  return (
    <Container>
      <Routes>
        <Route path="/:url" element={<Link />} />
        <Route path="*" element={<Home />} />
      </Routes>
    </Container>
  )
}

export default App
