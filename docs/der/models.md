# Models

## User
- Id
- Name
- Email
- Password (Obs: hash)

## Role
- Id
- Name

## Address
- Id
- UserId
- Street
- Number
- State

## Stock
- Id
- ProductId
- Quantity

## Ads
- Id
- Name
- ProductId

## Product
- Id
- Title
- Price
- DeletedAt

## Category
- Id
- Name

## Avaliation
- Id
- UserId
- ProductId
- Description
- Star

## Purchase
- Id
- UserId
- Total
- Status (Enum)
- CreatedAt

## PurchaseItem
- Id
- ProductId
- PurchaseId
- Quantity
