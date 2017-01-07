Rails.application.routes.draw do
  


  get 'buscar_paciente_enfermedad/index'

  get 'buscar_paciente_enfermedad/create'

  get 'buscar_paciente_cita/index'
  get 'buscar_paciente_cita/create'

  get 'buscarmedico/create'

  get 'buscarmedico/index'

  root  "inicio#index"

  get 'buscarpaciente/index'

  get 'buscarpaciente/create'

  resources :cita
  resources :enfermedads
  devise_for :medicos, :controllers => { :registrations => "medicos/registrations" }
  resources :pacientes do
    resources :cita, :controller => "paciente_citas" do
      resources :observacions, :controller => "paciente_cita_observacions"
    end
    resources :observacions, :controller => "paciente_observacions"
    resources :enfermedads, :controller => "paciente_enfermedades", only: [:index, :new, :create, :destroy]
  end
  resources :medicos, only: [:show, :edit, :update, :index, :destroy]
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  
  
  delete 'telefonos/:id(.:format)', :to => 'telefonos#destroy'
  delete 'emails/:id(.:format)', :to => 'emails#destroy'
  
  
  #devise_scope :medico do
  #  get "/medicos/sign_up" => "devise/registrations#new", as: "new_medico_registration" # custom path to sign_up/registration
  #end
  

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
