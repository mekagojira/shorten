export const Container = (props: { children: React.ReactNode }) => {
  return (
    <main className="bg-bg min-h-screen text-white">
      <nav className="py-4 bg-r border-slate-100 shadow-xl">
        <div className="container mx-auto md:px-0 px-3">
          <span className="text-lg font-bold">Shorten</span>
        </div>
      </nav>
      <div className="pb-4" />
      <div className="md:px-0 px-3 container mx-auto">{props.children}</div>
    </main>
  )
}
