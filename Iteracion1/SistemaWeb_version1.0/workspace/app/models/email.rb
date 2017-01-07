class Email < ActiveRecord::Base
  resourcify
  validates_format_of :email, :with => /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/i, :on => [ :create, :update ],  :message => "Ingresar de manera correcta" 
  belongs_to :paciente
  
end
