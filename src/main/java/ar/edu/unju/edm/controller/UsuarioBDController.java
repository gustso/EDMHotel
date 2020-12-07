package ar.edu.unju.edm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.unju.edm.model.User;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioBDController {
	@Autowired
	IUsuarioService usuarioService;
		
	@Autowired
	private User unUsuario;
	
	@RequestMapping("/usuarioBd")
	public String listaUsuariosLocalidades(Model model) {
		model.addAttribute("registroUsuario",new Usuario());		
		model.addAttribute("usuarios", usuarioService.obtenerUsuarios());			
		return "usuarioBD";
	}

	@PostMapping("/saveUsuario")
	public String GuardarUsuario(@Valid @ModelAttribute("registroUsuario") Usuario usuario, BindingResult result, ModelMap model)
	{
		if (result.hasErrors()) {
			model.addAttribute("registroUsuario", usuario);
			model.addAttribute("agregarUTab", "active");			
			model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
		}  else {
			
//			si pasa el primer if entonces hago la verificacion de que el nombre ingresado no se encuentre en la base de datos
//			invoco el metodo de buscar un determinado objeto y lo asigno a una variable 
			 Usuario coincidenciaUs = usuarioService.buscarUsuarioPorNombreUsuario(usuario.getNombreUsuario());
//			si la el resultado de coincidencias es diferente a null enctoces quiere decir que existen coincidencias
			 if(coincidenciaUs != null)
					{
						model.addAttribute("registroUsuario", new Usuario());						
						model.addAttribute("usuarios", usuarioService.obtenerUsuarios());						
						model.addAttribute("agregarUTab", "active");
						// activo el mensaje de la vista
						model.addAttribute("mensajeErrorUs1", true);
						// mando el mensaje de error
						model.addAttribute("errorUsuario1",
						"¡ATENCIÓN!	El Usuario ingresado ya existe en la base de datos");
					} else {
//							si pasa el 2do if compruebo que se hayan ingresado solo letras
//							variable de tipo boolean
							boolean band=false;
//							a la variable cadenaIngresada le asigno la cadena ingresada en el input nombre Real
							String cadenaIngresad= usuario.getNombreReal();
//							convierto cadena a miniscula y reemplazo acentos y espacios
							String cadenaIngresada=cadenaIngresad.toLowerCase().replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
									.replace("ú", "u").replace("Á", "a").replace("É", "e").replace("Í", "i").replace("Ó", "o")
									.replace("Ú", "u");
//							recorro la cadena
							for(int i=0;i<=cadenaIngresada.length()-1;i++)
							{
//								verifico que la cadena este entre los rangos correspondientes
								if(!(cadenaIngresada.charAt(i) > 96 && cadenaIngresada.charAt(i) < 123))
									band=true;
							}
//							si la badera es verdadera emito un nuevo mensaje de error
							if(band == true){
								model.addAttribute("registroUsuario", usuario);								
								model.addAttribute("usuarios", usuarioService.obtenerUsuarios());								
								model.addAttribute("agregarUTab", "active");
								// activo el mensaje de la vista
								model.addAttribute("mensajeErrorUs2", true);
								// mando el mensaje de error
								model.addAttribute("errorUsuario2",
								"¡ATENCIÓN! El campo nombre real solo admite letras!");		
							
								} else {
	//									si pasa el 3er if compruebo que se hayan ingresado solo letras
	//									variable de tipo boolean
										boolean band2=false;
	//									a la variable cadenaIngresada le asigno la cadena ingresada en el input apellido usuario
										String cadenaIngresad2= usuario.getApellidoReal();
//										convierto cadena a miniscula y reemplazo acentos y espacios
										String cadenaIngresada2=cadenaIngresad2.toLowerCase().replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
										.replace("ú", "u").replace("Á", "a").replace("É", "e").replace("Í", "i").replace("Ó", "o")
										.replace("Ú", "u");
	//									recorro la cadena
										for(int i=0;i<=cadenaIngresada2.length()-1;i++)
										{
	//										verifico que la cadena este entre los rangos correspondientes
											if(!(cadenaIngresada2.charAt(i) > 96 && cadenaIngresada2.charAt(i) < 123))
												band2=true;
										}
	//									si la badera es verdadera emito un nuevo mensaje de error
										if(band2 == true){
											model.addAttribute("registroUsuario", usuario);											
											model.addAttribute("usuarios", usuarioService.obtenerUsuarios());											
											model.addAttribute("agregarUTab", "active");
											// activo el mensaje de la vista
											model.addAttribute("mensajeErrorUs2b", true);
											// mando el mensaje de error
											model.addAttribute("errorUsuario2b",
											"¡ATENCIÓN! El campo nombre real solo admite letras!");}	
										else {											
	//											si pasa el 3er if verifico que la cadena ingresada del nombre no sea muy extensa
												if(cadenaIngresada.length()>20)
												{
													model.addAttribute("registroUsuario", usuario);												
													model.addAttribute("usuarios", usuarioService.obtenerUsuarios());													
													model.addAttribute("agregarUTab", "active");
													// activo el mensaje de la vista
													model.addAttribute("mensajeErrorUs3", true);
													// mando el mensaje de error
													model.addAttribute("errorUsuario3",
													"¡ATENCIÓN! El nombre real es muy largo!");																	
											} else
//												si pasa el 4to if verifico que la cadena ingresada del apellido no sea muy extensa
													if(cadenaIngresada2.length()>20)
													{
														model.addAttribute("registroUsuario", usuario);														
														model.addAttribute("usuarios", usuarioService.obtenerUsuarios());														
														model.addAttribute("agregarUTab", "active");
														// activo el mensaje de la vista
														model.addAttribute("mensajeErrorUs3b", true);
														// mando el mensaje de error
														model.addAttribute("errorUsuario3b",
														"¡ATENCIÓN! El nombre real es muy largo!");																	
														} else{
				//												finalmente si pasa todas las validaciones recien se podria estar en condiciones de guardar el usuario
																usuarioService.guardarUsuario(usuario);
																model.addAttribute("registroUsuario", unUsuario);																
																model.addAttribute("usuarios", usuarioService.obtenerUsuarios());																
																model.addAttribute("usuariosTab", "active");
															}		
													}						
											}
									}
							}
		return "usuarioBD";
	}
	
	@PostMapping("/saveUsuarioE")
	public String EditarUsuario(@Valid @ModelAttribute("registroUsuario") Usuario usuario, BindingResult result, ModelMap model)
	{
		if (result.hasErrors()) {
			model.addAttribute("registroUsuario", usuario);
			model.addAttribute("agregarUTab", "active");			
			model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
		}  else {
//							si pasa el 2do if compruebo que se hayan ingresado solo letras
//							variable de tipo boolean
							boolean band=false;
//							a la variable cadenaIngresada le asigno la cadena ingresada en el input nombre Real
							String cadenaIngresad= usuario.getNombreReal();
//							convierto cadena a miniscula y reemplazo acentos y espacios
							String cadenaIngresada=cadenaIngresad.toLowerCase().replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
									.replace("ú", "u").replace("Á", "a").replace("É", "e").replace("Í", "i").replace("Ó", "o")
									.replace("Ú", "u");
//							recorro la cadena
							for(int i=0;i<=cadenaIngresada.length()-1;i++)
							{
//								verifico que la cadena este entre los rangos correspondientes
								if(!(cadenaIngresada.charAt(i) > 96 && cadenaIngresada.charAt(i) < 123))
									band=true;
							}
//							si la badera es verdadera emito un nuevo mensaje de error
							if(band == true){
								model.addAttribute("registroUsuario", usuario);								
								model.addAttribute("usuarios", usuarioService.obtenerUsuarios());								
								model.addAttribute("agregarUTab", "active");
								// activo el mensaje de la vista
								model.addAttribute("mensajeErrorUs2", true);
								// mando el mensaje de error
								model.addAttribute("errorUsuario2",
								"¡ATENCIÓN! El campo nombre real solo admite letras!");									
								} else {
	//									si pasa el 3er if compruebo que se hayan ingresado solo letras
	//									variable de tipo boolean
										boolean band2=false;
	//									a la variable cadenaIngresada le asigno la cadena ingresada en el input apellido usuario
										String cadenaIngresad2= usuario.getApellidoReal();
//										convierto cadena a miniscula y reemplazo acentos y espacios
										String cadenaIngresada2=cadenaIngresad2.toLowerCase().replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
												.replace("ú", "u").replace("Á", "a").replace("É", "e").replace("Í", "i").replace("Ó", "o")
												.replace("Ú", "u");
	//									recorro la cadena
										for(int i=0;i<=cadenaIngresada2.length()-1;i++)
										{
	//										verifico que la cadena este entre los rangos correspondientes
											if(!(cadenaIngresada2.charAt(i) > 96 && cadenaIngresada2.charAt(i) < 123))
												band2=true;
										}
	//									si la badera es verdadera emito un nuevo mensaje de error
										if(band2 == true){
											model.addAttribute("registroUsuario", usuario);											
											model.addAttribute("usuarios", usuarioService.obtenerUsuarios());											
											model.addAttribute("agregarUTab", "active");
											// activo el mensaje de la vista
											model.addAttribute("mensajeErrorUs2b", true);
											// mando el mensaje de error
											model.addAttribute("errorUsuario2b",
											"¡ATENCIÓN! El campo nombre real solo admite letras!");}	
										else {											
	//											si pasa el 3er if verifico que la cadena ingresada del nombre no sea muy extensa
												if(cadenaIngresada.length()>20)
												{
													model.addAttribute("registroUsuario", usuario);												
													model.addAttribute("usuarios", usuarioService.obtenerUsuarios());													
													model.addAttribute("agregarUTab", "active");
													// activo el mensaje de la vista
													model.addAttribute("mensajeErrorUs3", true);
													// mando el mensaje de error
													model.addAttribute("errorUsuario3",
													"¡ATENCIÓN! El nombre real es muy largo!");				
													
											} else
//												si pasa el 4to if verifico que la cadena ingresada del apellido no sea muy extensa
													if(cadenaIngresada2.length()>20)
													{
														model.addAttribute("registroUsuario", usuario);														
														model.addAttribute("usuarios", usuarioService.obtenerUsuarios());														
														model.addAttribute("agregarUTab", "active");
														// activo el mensaje de la vista
														model.addAttribute("mensajeErrorUs3b", true);
														// mando el mensaje de error
														model.addAttribute("errorUsuario3b",
														"¡ATENCIÓN! El nombre real es muy largo!");																	
														} else{
				//												finalmente si pasa todas las validaciones recien se podria estar en condiciones de guardar el usuario
																usuarioService.guardarUsuario(usuario);
																model.addAttribute("registroUsuario", unUsuario);																
																model.addAttribute("usuarios",usuarioService.obtenerUsuarios());																
																model.addAttribute("usuariosTab", "active");
																return "usuarioBD";
															}		
													}						
											}
									
							}
		return "editarUsuario";
	}
	
	@GetMapping("/usuarioBdU")
	public String listaUsuarios(Model model) {
		model.addAttribute("usuariosTab", "active");
		model.addAttribute("registroUsuario", new Usuario());		
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();		
		model.addAttribute("usuarios", usuarios);		
		return "usuarioBD";
	}			

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Optional<Usuario> usuario = usuarioService.obtenerUnUsuario(id);
		model.addAttribute("registroUsuario", usuario);
		return "editarUsuario";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, Model model) {
		usuarioService.eliminarUsuario(id);
		return "redirect:/usuarioBdU";
	}
	
	@GetMapping("/cancelarUs")
	public String cancelarUsuario(ModelMap model) {
		return "redirect:/usuarioBdU";
	}	
}
