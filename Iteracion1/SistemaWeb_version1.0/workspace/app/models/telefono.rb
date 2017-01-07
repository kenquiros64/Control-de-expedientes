class Telefono < ActiveRecord::Base
  resourcify
  validates_format_of :telefono, :with => /([0-9]){4}-([0-9]){4}/,:on => [ :create, :update ],  :message => "Ingresar de manera correcta"
  belongs_to :paciente
end
