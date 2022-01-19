object Utilities {
  def slpClr(seconds: Int): Unit ={
    Thread.sleep(seconds * 1000)
    print("\r")
  }

  def line(): Unit = {
    print("\n~~~~~~~~~~\n")
  }
}
