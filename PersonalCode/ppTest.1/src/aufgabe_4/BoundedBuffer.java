package aufgabe_4;

public class BoundedBuffer<T> {
   final Object[] items = new Object[8];
   int putptr, takeptr, count;

   public void put(final T x) throws InterruptedException {
         if(this.count == this.items.length){
            a1111111111();
      }  
      this.items[this.putptr] = x;
      if(++this.putptr == this.items.length) {
         this.putptr = 0;
      }
      ++this.count;
      b2222222222();
      }


   public T take() throws InterruptedException {
         if(this.count == 0) {
            c3333333333();
         }
         final T x = (T) this.items[this.takeptr];
         if( ++this.takeptr == this.items.length) {
            this.takeptr = 0;
         }
         --this.count;
         d4444444444();
         return x;
   }
   

   public void a1111111111() throws InterruptedException{
      wait();
   }
   private void b2222222222() {
      notifyAll();
   }
   private void c3333333333() throws InterruptedException{
      wait();
   }
   private void d4444444444() {
      notifyAll();
   }
}