# javaRayTracer

In this project I will code a little ray tracer in good old plain Java.

## Features
### Antialising
Antialising is implemented and can be adjusted by samples per pixel.\
![Antialising!](docs/antialising.png)

### Diffuse materials
Diffuse materials are supported which modulate light with their own base color. It reflects child rays of light in random directions with the true lambertian reflection method.\
![Diffuse Material!](docs/diffusemat.png)

### Metal materials
Metal materials are implemented with a new scattered reflection method. Their fuzziness can be parameterized.\
 ![Diffuse Material!](docs/metalmat.png)
## Reference
As a reference I'm using this nice book about raytracing: https://raytracing.github.io/books/RayTracingInOneWeekend.html
