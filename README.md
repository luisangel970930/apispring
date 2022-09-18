### Cosas Que faltan:

* Agregar esta direccion a swagger : http://localhost:8081/api/ecasa/login
**Para obtener el token desde ahi ya que no esta dentro de el pakete de controlador que es lo que carga el swagger**


## En cada controlador  devolver siempre algo como esto a menos que obtengas una lista o un objeto:

 **Answer res = new Answer();
  try {
  productService.addProduct(product,idCategory);
  res.setResponse("Successful");
  return new ResponseEntity<Answer>(res, HttpStatus.OK);
  }catch (EntityNotFoundException e){
  res.setResponse("Error");
  return new ResponseEntity<Answer>(res, HttpStatus.NOT_FOUND);
  }** 

##  Faltan controller como Eliminar producto ,Update cantidad del producto ,etc

